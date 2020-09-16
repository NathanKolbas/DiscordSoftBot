require 'spec_helper'
require 'discord_soft_webhook'

RSpec.describe DiscordSoftWebhook do
  describe '#initialize' do
    subject { DiscordSoftWebhook.new(canvas_token, discord_webhook, course_id, slack_webhook) }
    let(:canvas_token) { rand }
    let(:discord_webhook) { rand }
    let(:course_id) { rand }
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

    describe 'when missing course id' do
      let(:course_id) { nil }

      it 'errors' do
        expect { subject }.to raise_error('No course ID provided')
      end
    end

    describe 'when no slack webhook is provided' do
      let(:slack_webhook) { nil }

      it 'does not error' do
        subject
      end
    end
  end

  describe '#past_assignments' do
    subject { DiscordSoftWebhook.new(rand, rand, rand).past_assignments }
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
      allow(File).to receive(:read).with('CurrentAssignments.json') { to_dos.to_json }
    end

    describe 'File does not exist' do
      let(:file_exists) { false }

      it 'returns nil' do
        expect(subject).to be_nil
      end
    end

    describe 'reads file' do
      it 'returns a symbolized hash' do
        expect(subject[0]).to eq(to_dos[0])
      end
    end
  end

  describe '#run' do
    subject { dsw.run(assignments, old_assignments) }
    let(:dsw) { DiscordSoftWebhook.new(rand, rand, rand) }
    let(:assignments) do
      [
        {
          "id": 1,
          "due_at": due_time.to_s,
          "name": 'Quiz - Week 1',
          "html_url": 'https://canvas.unl.edu/courses/1/assignments/1'
        }
      ]
    end
    let(:old_assignments) { nil }

    describe 'the assignment message was not sent before' do
      describe 'the assignment is due tomorrow' do
        let(:due_time) { DateTime.now + 1 }

        it 'sends a message' do
          expect(dsw).to receive(:send_message).with(
            "The assignment is due tomorrow at #{due_time.strftime('%l:%M %p').strip}",
            assignments[0][:name],
            assignments[0][:html_url]
          )
          allow(File).to receive(:write)
          subject
        end
      end

      describe 'the assignment is due today' do
        let(:due_time) { DateTime.now }

        it 'sends a message' do
          expect(dsw).to receive(:send_message).with(
            "The assignment is due today at #{due_time.strftime('%l:%M %p').strip}",
            assignments[0][:name],
            assignments[0][:html_url]
          )
          allow(File).to receive(:write)
          subject
        end
      end

      describe 'the assignment is due in 7 days' do
        let(:due_time) { DateTime.now + 7 }

        it 'sends a message' do
          due_date = due_time.strftime('%m/%d/%Y at ') + due_time.strftime('%l:%M %p').strip
          expect(dsw).to receive(:send_message).with(
            "The assignment is due in 7 days on #{due_date}",
            assignments[0][:name],
            assignments[0][:html_url]
          )
          allow(File).to receive(:write)
          subject
        end
      end
    end

    describe 'the assignment message was already sent' do
      let(:old_assignments) do
        [
          {
            "id": 1
          }
        ]
      end

      describe 'the assignment is due in one week' do
        let(:due_time) { DateTime.now + 7 }

        it 'sends a message' do
          due_date = due_time.strftime('%m/%d/%Y at ') + due_time.strftime('%l:%M %p').strip
          expect(dsw).to receive(:send_message).with(
            "The assignment is due in one week on #{due_date}",
            assignments[0][:name],
            assignments[0][:html_url]
          )
          allow(File).to receive(:write)
          subject
        end
      end

      describe 'the assignment is due in one week' do
        let(:due_time) { DateTime.now + 1 }

        it 'sends a message' do
          expect(dsw).to receive(:send_message).with(
              "The assignment is due tomorrow at #{due_time.strftime('%l:%M %p').strip}",
              assignments[0][:name],
              assignments[0][:html_url]
          )
          allow(File).to receive(:write)
          subject
        end
      end
    end
  end
end
