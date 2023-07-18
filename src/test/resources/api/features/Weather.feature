@Weather
Feature: Checking weather API


  Scenario Outline: Checking success response for current weather
    When Request is sent for current weather:
      | query   | units   |
      | <query> | <units> |
    Then Checking success response:
      | query    | type   | name   | country   | language | utcOffset   | temperature   | windSpeed   |
      | <queryR> | <type> | <name> | <country> | en       | <utcOffset> | <temperature> | <windSpeed> |
    Scenarios:
      | query               | units | queryR                  | type    | name      | country        | utcOffset | temperature | windSpeed |
      | 10001               | m     | 10001                   | Zipcode | New York  | USA            | -4.0      | -?\d+       | \d+       |
      | 51.507359,-0.136439 | s     | Lat 51.51 and Lon -0.14 | LatLon  | London    | United Kingdom | 1.0       | \d+         | \d+       |
      | 101.100.160.0       | f     | 101.100.160.0           | IP      | Singapore | Singapore      | 8.0       | -?\d+       | \d+       |
      | Nuuk                | m     | Nuuk, Greenland         | City    | Nuuk      | Greenland      | -2.0      | -?\d+       | \d+       |
      # intentional fail
      | Tokyo               | s     | 12345                   | City    | Tokyo     | Japan          | GMT+9     | -\d+        | -\d+      |


  Scenario Outline: Checking fail response for current weather
    When Request is sent for current weather:
      | query   | language   |
      | <query> | <language> |
    Then Checking fail response:
      | success   | code   | type   | info   |
      | <success> | <code> | <type> | <info> |
    Scenarios:
      | query           | language | success | code | type                               | info                                                                                                           |
      | 10001           | de       | false   | 105  | function_access_restricted         | Access Restricted - Your current Subscription Plan does not support this API Function.                         |
      |                 |          | false   | 601  | missing_query                      | Please specify a valid location identifier using the query parameter.                                          |
      | Tokyo;Singapore |          | false   | 604  | bulk_queries_not_supported_on_plan | Your current subscription plan does not support bulk queries. Please upgrade your account to use this feature. |
      | Zurbagan        |          | false   | 615  | request_failed                     | Your API request failed. Please try again or contact support.                                                  |
