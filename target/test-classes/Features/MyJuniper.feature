@MyJuniper
Feature: MyJuniper Application Flow Check

Scenario: Verify MyJuniper Application

Given Open MyJuniper Application
When Is Login success
Then Enter Customer Email
Then Verify All Mandatory Parent Tabs are present
Then Verify All Tabs are working
Then Verify Overview Tab's data with other Mandatory Tab's data
Then Verify Mandatory Tab's data with their child Tab's data