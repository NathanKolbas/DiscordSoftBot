require 'spec_helper'
require 'discord_soft_webhook'

RSpec.describe DiscordSoftWebhook do
  describe '#initialize' do
    subject { DiscordSoftWebhook.new(canvas_token, discord_webhook, slack_webhook) }
    let(:canvas_token) { rand }
    let(:discord_webhook) { rand }
    let(:slack_webhook) { rand }

    it 'is successful' do
      subject
    end

    describe 'when missing canvas token' do
      let(:canvas_token) { nil }

      it 'errors' do
        expect { subject }.to raise_error('No canvas token provided')
      end
    end

    describe 'when missing discord webhook' do
      let(:discord_webhook) { nil }

      it 'errors' do
        expect { subject }.to raise_error('No Discord webhook URL provided')
      end
    end

    describe 'when no slack webhook is provided' do
      let(:slack_webhook) { nil }

      it 'does not error' do
        subject
      end
    end
  end

  describe '#past_todos' do
    subject { DiscordSoftWebhook.new(rand, rand).past_todos }
    let(:file_exists) { true }
    let(:to_dos) do
      [
        {
          "assignment": {
            "id": 1,
            "due_at": '2020-01-1T22:00:00Z',
            "name": 'Quiz - Week 1',
            "html_url": 'https://canvas.unl.edu/courses/1/assignments/1'
          }
        }
      ]
    end

    before :each do
      allow(File).to receive(:exist?) { file_exists }
      allow(File).to receive(:read).with('CurrentToDos.json') { to_dos.to_json }
    end

    describe 'File does not exist' do
      let(:file_exists) { false }

      it 'returns nil' do
        expect(subject).to be_nil
      end
    end

    describe 'reads file' do
      it 'returns a symbolized hash' do
        expect(subject[0][:assignment]).to eq(to_dos[0][:assignment])
      end
    end
  end
end
