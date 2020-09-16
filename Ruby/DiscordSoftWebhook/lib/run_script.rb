require_relative 'discord_soft_webhook'

# This script will create an instance of discord_soft_webhook which will handle the sending out of new assignments to
# Discord

# To change text color
class String
  # colorization
  def colorize(color_code)
    "\e[#{color_code}m#{self}\e[0m"
  end

  def red
    colorize(31)
  end

  def green
    colorize(32)
  end

  def yellow
    colorize(33)
  end

  def blue
    colorize(34)
  end

  def light_blue
    colorize(36)
  end
end

def required_args_msg(error_msg)
  canvas = "1. The canvas API token \n"
  discord = "2. Discord webhook URL\n"
  course = "3. Canvas Course ID to get assignments for the course\n"
  inputs = [canvas, discord, course]

  msg = "\nTo start the messaging script you need to ".yellow
  msg << "provide these arguments in this order:\n".yellow
  3.times { |i| msg << (ARGV[i] ? inputs[i].green : inputs[i].red) }
  msg << "4. Slack webhook URL for error messages (optional)\n\n".light_blue
  puts msg

  raise error_msg
end

required_args_msg 'No canvas token provided' unless ARGV[0]
required_args_msg 'No Discord webhook URL provided' unless ARGV[1]
required_args_msg 'No course ID provided' unless ARGV[2]

canvas_token = ARGV[0]
discord_webhook = ARGV[1]
course_id = ARGV[2]
slack_webhook = ARGV[3]

dsw = DiscordSoftWebhook.new(canvas_token, discord_webhook, course_id, slack_webhook)
assignments = dsw.request_canvas_assignments
past_assignments = dsw.past_assignments
dsw.run(assignments, past_assignments)
