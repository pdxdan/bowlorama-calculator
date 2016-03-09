require 'httparty'
require 'json'

When(/^the player's only ball was a (\d+)$/) do |roll|
  @result = HTTParty.post("https://oarmnm6fg3.execute-api.us-west-2.amazonaws.com/prod/bowlorama-score",
                          :body => { :rolls => roll}.to_json,
                          :headers => { 'Content-Type' => 'application/json' } )
end

When(/^the player bowls (.*)/) do |rolls|
  @result = HTTParty.post("https://oarmnm6fg3.execute-api.us-west-2.amazonaws.com/prod/bowlorama-score",
                          :body => { :rolls => rolls}.to_json,
                          :headers => { 'Content-Type' => 'application/json' } )
end

Then(/^the score should be (\d+)$/) do |score|
  expect(JSON.parse(@result.body) ["score"]).to eq score.to_i
end

