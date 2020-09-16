require 'bundler/setup'
require 'simplecov'

SimpleCov.coverage_dir('spec/log/coverage')
SimpleCov.minimum_coverage 89.33 # Once there is full coverage change this to 100
SimpleCov.start

RSpec.configure do |config|
  # Enable flags like --only-failures and --next-failure
  config.example_status_persistence_file_path = '/spec/log/.rspec_status'

  # Disable RSpec exposing methods globally on `Module` and `main`
  config.disable_monkey_patching!

  config.expect_with :rspec do |c|
    c.syntax = :expect
  end
end
