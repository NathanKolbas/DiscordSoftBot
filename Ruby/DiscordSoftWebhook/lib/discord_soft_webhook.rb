require 'date'
require 'discord_notifier'
require 'httparty'
require 'slack-notifier'
require 'json'

# Sends out a Discord message with the todos for SOFT classes
class DiscordSoftWebhook
  attr_accessor :canvas_token, :discord_webhook, :slack_webhook, :course_id
  JSON_FILE_NAME = 'CurrentToDos.json'.freeze
  SOFT161_ID = 80_912
  SOFT260_ID = nil

  def initialize(canvas_token, discord_webhook, course_id, slack_webhook = nil)
    # Validation
    raise 'No canvas token provided' unless canvas_token
    raise 'No Discord webhook URL provided' unless discord_webhook
    raise 'No course ID provided' unless course_id

    # Set vars
    @canvas_token = canvas_token
    @discord_webhook = discord_webhook
    @course_id = course_id
    @slack_webhook = slack_webhook

    # Setup discord
    Discord::Notifier.setup do |config|
      config.url = @discord_webhook
      config.username = 'Software Engineering Assignments Bot'
      config.wait = true
    end
  end

  def request_canvas_todos
    url = "https://canvas.unl.edu/api/v1/courses/#{@course_id}/todo/"
    headers = {
      'Authorization': "Bearer #{@canvas_token}",
      'Content-Type': 'application/json',
      'Accept': 'application/json',
      'User-Agent': 'Mozilla/5.0'
    }
    response = HTTParty.get(url, headers: headers)
    unless response.success?
      err_msg = "Error getting todos from Canvas: {code: #{response.code}, body: #{response.body}}"
      raise SendSlackException, @slack_webhook, err_msg
    end

    JSON.parse(response.body, symbolize_names: true)
  end

  def past_todos
    JSON.parse(File.read(JSON_FILE_NAME), symbolize_names: true) if File.exist? JSON_FILE_NAME
  end

  def days_until(date)
    (date - DateTime.now).round
  end

  def send_message(des, title, url, color: '#FF0000')
    embed = Discord::Embed.new do
      color color
      description des
      title       title
      url         url
    end
    Discord::Notifier.message(embed)
  end

  def run(todos, old_todos)
    raise SendSlackException, @slack_webhook, 'No todos received' unless todos

    old_ids = old_todos&.map { |t| t[:assignment][:id] }
    todos.each do |t|
      # Check each to-do
      # Currently setup to send out when assigned, one week, and the day before.
      due_date = DateTime.parse(t[:assignment][:due_at])
      assignment_name = t[:assignment][:name]
      assigment_link = t[:assignment][:html_url]
      due_time = due_date.strftime('%l:%M %p')
      due_date_formatted = due_date.strftime('%m/%d/%Y at %l:%M %p')
      diff_in_days = days_until(due_date)
      des = nil

      if old_ids&.include? t[:assignment][:id]
        if diff_in_days == 7
          # 7 day until the assignment is due
          des = "The assignment is due in one week on #{due_date_formatted}"
        elsif diff_in_days == 1
          # 1 day until the assignment is due
          des = "The assignment is due tomorrow at #{due_time}"
        end
      else
        des = case diff_in_days
              when 1
                "The assignment is due tomorrow at #{due_time}"
              when 0
                "The assignment is due today at #{due_time}"
              else
                "The assignment is due in #{diff_in_days} days on #{due_date_formatted}"
              end
      end
      send_message(des, assignment_name, assigment_link) if des
    end

    File.write(JSON_FILE_NAME, todos.to_json)
  end
end

# For custom error
class SendSlackException < StandardError
  def initialize(msg, slack_webhook = nil, exception_type = 'error_with_slack')
    # Send Slack message with the error
    Slack::Notifier.new(slack_webhook).ping msg if slack_webhook

    @exception_type = exception_type
    super(msg)
  end
end
