require 'httparty'

class DiscordSoftWebhook
    attr_accessor :canvas_token, :discord_webhook, :slack_webhook
    SOFT161_ID = 80912
    SOFT260_ID = nil

    def initialize
        # Validation
        raise 'No canvas token provided' unless ARGV[0]
        raise 'No Discord webhook URL provided' unless ARGV[1]
        raise 'No slack webhook URL provided' unless ARGV[2]

        # Set vars
        @canvas_token = ARGV[0]
        @discord_webhook = ARGV[1]
        @slack_webhook = ARGV[2]
    end

    def get_canvas_todos
        url = "https://canvas.unl.edu/api/v1/courses/#{SOFT161_ID}/todo/"
        headers = {
            'Authorization': "Bearer #{@canvas_token}",
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'User-Agent': 'Mozilla/5.0'
        }
        response = HTTParty.get(url, headers: headers)
        puts response
    end
end

# This runs the script by starting in initialize of the class
test = DiscordSoftWebhook.new
test.get_canvas_todos
