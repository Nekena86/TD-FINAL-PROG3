

# MembersPostRequest


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**firstName** | **String** |  |  |
|**lastName** | **String** |  |  |
|**birthDate** | **LocalDate** |  |  |
|**gender** | [**GenderEnum**](#GenderEnum) |  |  |
|**address** | **String** |  |  |
|**job** | **String** |  |  |
|**phone** | **String** |  |  |
|**email** | **String** |  |  |
|**collectivityId** | **String** | Target collectivity |  |
|**sponsors** | [**List&lt;MembersPostRequestSponsorsInner&gt;**](MembersPostRequestSponsorsInner.md) | Must contain at least 2 confirmed members from federations |  |
|**payment** | [**MembersPostRequestPayment**](MembersPostRequestPayment.md) |  |  |



## Enum: GenderEnum

| Name | Value |
|---- | -----|
| F | &quot;F&quot; |
| M | &quot;M&quot; |



