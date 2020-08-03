require_relative 'discord_soft_webhook'

# This script will create an instance of discord_soft_webhook which will handle the sending out of new todos to Discord

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
end

def required_args_msg(error_msg)
  msg = "\nTo start the messaging script you need to ".yellow
  msg << "provide these arguments in this order:\n".yellow
  msg << "1 = The canvas API token \n2 = Discord webhook URL".blue
  msg << "\n3 = Slack webhook URL for error messages (optional)\n".blue
  puts msg
  raise error_msg
end

required_args_msg 'No canvas token provided' unless ARGV[0]
required_args_msg 'No Discord webhook URL provided' unless ARGV[1]

canvas_token = ARGV[0]
discord_webhook = ARGV[1]
slack_webhook = ARGV[2]

dsw = DiscordSoftWebhook.new(canvas_token, discord_webhook, slack_webhook)
todos = dsw.request_canvas_todos
past_todos = dsw.past_todos
dsw.run(todos, past_todos)
