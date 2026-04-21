

# ContributionsPostRequest


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**memberId** | **String** |  |  |
|**amount** | **BigDecimal** |  |  |
|**date** | **LocalDate** |  |  |
|**type** | [**TypeEnum**](#TypeEnum) |  |  |
|**paymentMode** | [**PaymentModeEnum**](#PaymentModeEnum) |  |  |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| MONTHLY | &quot;monthly&quot; |
| ANNUAL | &quot;annual&quot; |
| SPECIAL | &quot;special&quot; |



## Enum: PaymentModeEnum

| Name | Value |
|---- | -----|
| CASH | &quot;cash&quot; |
| BANK | &quot;bank&quot; |
| MOBILE_MONEY | &quot;mobile_money&quot; |



