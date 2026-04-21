# DefaultApi

All URIs are relative to *http://localhost*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**accountsPost**](DefaultApi.md#accountsPost) | **POST** /accounts | Create a financial account |
| [**activitiesPost**](DefaultApi.md#activitiesPost) | **POST** /activities | Create an activity |
| [**attendancePost**](DefaultApi.md#attendancePost) | **POST** /attendance | Record attendance for an activity |
| [**collectivitiesPost**](DefaultApi.md#collectivitiesPost) | **POST** /collectivities | Open a new agricultural collectivity |
| [**contributionsPost**](DefaultApi.md#contributionsPost) | **POST** /contributions | Record a member contribution |
| [**membersPost**](DefaultApi.md#membersPost) | **POST** /members | Admit a new member into a collectivity (B-2 updated rules) |
| [**statisticsCollectivityIdGet**](DefaultApi.md#statisticsCollectivityIdGet) | **GET** /statistics/collectivity/{id} | Get collectivity statistics |
| [**statisticsFederationGet**](DefaultApi.md#statisticsFederationGet) | **GET** /statistics/federation | Get global federation monthly/annual report |


<a id="accountsPost"></a>
# **accountsPost**
> accountsPost(accountsPostRequest)

Create a financial account

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    AccountsPostRequest accountsPostRequest = new AccountsPostRequest(); // AccountsPostRequest | 
    try {
      apiInstance.accountsPost(accountsPostRequest);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#accountsPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **accountsPostRequest** | [**AccountsPostRequest**](AccountsPostRequest.md)|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Account created |  -  |

<a id="activitiesPost"></a>
# **activitiesPost**
> activitiesPost(activitiesPostRequest)

Create an activity

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    ActivitiesPostRequest activitiesPostRequest = new ActivitiesPostRequest(); // ActivitiesPostRequest | 
    try {
      apiInstance.activitiesPost(activitiesPostRequest);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#activitiesPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **activitiesPostRequest** | [**ActivitiesPostRequest**](ActivitiesPostRequest.md)|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Activity created |  -  |

<a id="attendancePost"></a>
# **attendancePost**
> attendancePost(attendancePostRequest)

Record attendance for an activity

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    AttendancePostRequest attendancePostRequest = new AttendancePostRequest(); // AttendancePostRequest | 
    try {
      apiInstance.attendancePost(attendancePostRequest);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#attendancePost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **attendancePostRequest** | [**AttendancePostRequest**](AttendancePostRequest.md)|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Attendance recorded |  -  |

<a id="collectivitiesPost"></a>
# **collectivitiesPost**
> collectivitiesPost(collectivitiesPostRequest)

Open a new agricultural collectivity

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    CollectivitiesPostRequest collectivitiesPostRequest = new CollectivitiesPostRequest(); // CollectivitiesPostRequest | 
    try {
      apiInstance.collectivitiesPost(collectivitiesPostRequest);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#collectivitiesPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **collectivitiesPostRequest** | [**CollectivitiesPostRequest**](CollectivitiesPostRequest.md)|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Collectivity created |  -  |

<a id="contributionsPost"></a>
# **contributionsPost**
> contributionsPost(contributionsPostRequest)

Record a member contribution

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    ContributionsPostRequest contributionsPostRequest = new ContributionsPostRequest(); // ContributionsPostRequest | 
    try {
      apiInstance.contributionsPost(contributionsPostRequest);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#contributionsPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **contributionsPostRequest** | [**ContributionsPostRequest**](ContributionsPostRequest.md)|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Contribution recorded |  -  |

<a id="membersPost"></a>
# **membersPost**
> membersPost(membersPostRequest)

Admit a new member into a collectivity (B-2 updated rules)

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    MembersPostRequest membersPostRequest = new MembersPostRequest(); // MembersPostRequest | 
    try {
      apiInstance.membersPost(membersPostRequest);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#membersPost");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **membersPostRequest** | [**MembersPostRequest**](MembersPostRequest.md)|  | |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **201** | Member admitted |  -  |

<a id="statisticsCollectivityIdGet"></a>
# **statisticsCollectivityIdGet**
> statisticsCollectivityIdGet(id, startDate, endDate)

Get collectivity statistics

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String id = "id_example"; // String | 
    LocalDate startDate = LocalDate.now(); // LocalDate | 
    LocalDate endDate = LocalDate.now(); // LocalDate | 
    try {
      apiInstance.statisticsCollectivityIdGet(id, startDate, endDate);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#statisticsCollectivityIdGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **String**|  | |
| **startDate** | **LocalDate**|  | [optional] |
| **endDate** | **LocalDate**|  | [optional] |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Statistics returned |  -  |

<a id="statisticsFederationGet"></a>
# **statisticsFederationGet**
> StatisticsFederationGet200Response statisticsFederationGet(startDate, endDate)

Get global federation monthly/annual report

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    LocalDate startDate = LocalDate.now(); // LocalDate | 
    LocalDate endDate = LocalDate.now(); // LocalDate | 
    try {
      StatisticsFederationGet200Response result = apiInstance.statisticsFederationGet(startDate, endDate);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#statisticsFederationGet");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **startDate** | **LocalDate**|  | [optional] |
| **endDate** | **LocalDate**|  | [optional] |

### Return type

[**StatisticsFederationGet200Response**](StatisticsFederationGet200Response.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Federation statistics report |  -  |

