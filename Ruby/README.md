# DiscordSoftWebhook

## Usage

This is used for sending wonderful and timely reminders on when a software engineering assignment is due at [UNL](https://www.unl.edu/). The script is not limited to our software engineering course since the arguments are free to be changed to whatever suits you. In other words, it could also be used for notifying when assignments are due in other classes on Canvas. This is done by sending out a Discord message to our server. The `run_script.rb` file is what performs these tasks. Run that script and specify the Canvas token, Discord webhook URL, Canvas course ID, and optionally the Slack webhook URL if you would like to report errors (ie `Ruby ./lib/run_script.rb canvas_token discord_webhook slack_webhook`).

## Development

After checking out the repo, run `bundle install` to install dependencies. Then, run `rake spec` to run the tests. Coverage reports are located in `/spec/log/coverage/index.html` and are generated when running `rake spec`. LOC is enforced but is currently not 100%. You can find the code and the DiscordSoftWebhook class located in the `/lib/` directory. Rubocop style checks are also enforced and can be changed in `.rubocop.yml`.
