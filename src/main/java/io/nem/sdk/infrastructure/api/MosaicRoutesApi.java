/*
 * Copyright 2019 NEM
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * Catapult REST API Reference
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 0.7.15
 *
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

package io.nem.sdk.infrastructure.api;

import com.google.gson.reflect.TypeToken;
import io.nem.sdk.infrastructure.ApiCallback;
import io.nem.sdk.infrastructure.ApiClient;
import io.nem.sdk.infrastructure.ApiException;
import io.nem.sdk.infrastructure.ApiResponse;
import io.nem.sdk.infrastructure.Configuration;
import io.nem.sdk.infrastructure.Pair;
import io.nem.sdk.infrastructure.model.MosaicIds;
import io.nem.sdk.infrastructure.model.MosaicInfoDTO;
import io.nem.sdk.infrastructure.model.MosaicNamesDTO;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MosaicRoutesApi {

    private ApiClient localVarApiClient;

    public MosaicRoutesApi() {
        this(Configuration.getDefaultApiClient());
    }

    public MosaicRoutesApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    /**
     * Build call for getMosaic
     *
     * @param mosaicId The mosaic identifier. (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> success </td><td>  -  </td></tr>
     * <tr><td> 404 </td><td> resource not found </td><td>  -  </td></tr>
     * <tr><td> 409 </td><td> invalid argument </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call getMosaicCall(String mosaicId, final ApiCallback _callback)
        throws ApiException {
        Object localVarPostBody = new Object();

        // create path and map variables
        String localVarPath =
            "/mosaic/{mosaicId}"
                .replaceAll(
                    "\\{" + "mosaicId" + "\\}", localVarApiClient.escapeString(mosaicId));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {"application/json"};
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {};

        final String localVarContentType =
            localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[]{};
        return localVarApiClient.buildCall(
            localVarPath,
            "GET",
            localVarQueryParams,
            localVarCollectionQueryParams,
            localVarPostBody,
            localVarHeaderParams,
            localVarFormParams,
            localVarAuthNames,
            _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getMosaicValidateBeforeCall(String mosaicId, final ApiCallback _callback)
        throws ApiException {

        // verify the required parameter 'mosaicId' is set
        if (mosaicId == null) {
            throw new ApiException(
                "Missing the required parameter 'mosaicId' when calling getMosaic(Async)");
        }

        okhttp3.Call localVarCall = getMosaicCall(mosaicId, _callback);
        return localVarCall;
    }

    /**
     * Get mosaic information Gets the mosaic definition for a given mosaicId.
     *
     * @param mosaicId The mosaic identifier. (required)
     * @return MosaicInfoDTO
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     * response body
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> success </td><td>  -  </td></tr>
     * <tr><td> 404 </td><td> resource not found </td><td>  -  </td></tr>
     * <tr><td> 409 </td><td> invalid argument </td><td>  -  </td></tr>
     * </table>
     */
    public MosaicInfoDTO getMosaic(String mosaicId) throws ApiException {
        ApiResponse<MosaicInfoDTO> localVarResp = getMosaicWithHttpInfo(mosaicId);
        return localVarResp.getData();
    }

    /**
     * Get mosaic information Gets the mosaic definition for a given mosaicId.
     *
     * @param mosaicId The mosaic identifier. (required)
     * @return ApiResponse&lt;MosaicInfoDTO&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     * response body
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> success </td><td>  -  </td></tr>
     * <tr><td> 404 </td><td> resource not found </td><td>  -  </td></tr>
     * <tr><td> 409 </td><td> invalid argument </td><td>  -  </td></tr>
     * </table>
     */
    public ApiResponse<MosaicInfoDTO> getMosaicWithHttpInfo(String mosaicId) throws ApiException {
        okhttp3.Call localVarCall = getMosaicValidateBeforeCall(mosaicId, null);
        Type localVarReturnType = new TypeToken<MosaicInfoDTO>() {
        }.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get mosaic information (asynchronously) Gets the mosaic definition for a given mosaicId.
     *
     * @param mosaicId The mosaic identifier. (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body
     * object
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> success </td><td>  -  </td></tr>
     * <tr><td> 404 </td><td> resource not found </td><td>  -  </td></tr>
     * <tr><td> 409 </td><td> invalid argument </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call getMosaicAsync(String mosaicId, final ApiCallback<MosaicInfoDTO> _callback)
        throws ApiException {

        okhttp3.Call localVarCall = getMosaicValidateBeforeCall(mosaicId, _callback);
        Type localVarReturnType = new TypeToken<MosaicInfoDTO>() {
        }.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    /**
     * Build call for getMosaics
     *
     * @param mosaicIds (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> success </td><td>  -  </td></tr>
     * <tr><td> 400 </td><td> invalid content </td><td>  -  </td></tr>
     * <tr><td> 409 </td><td> invalid argument </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call getMosaicsCall(MosaicIds mosaicIds, final ApiCallback _callback)
        throws ApiException {
        Object localVarPostBody = mosaicIds;

        // create path and map variables
        String localVarPath = "/mosaic";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {"application/json"};
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {};

        final String localVarContentType =
            localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[]{};
        return localVarApiClient.buildCall(
            localVarPath,
            "POST",
            localVarQueryParams,
            localVarCollectionQueryParams,
            localVarPostBody,
            localVarHeaderParams,
            localVarFormParams,
            localVarAuthNames,
            _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getMosaicsValidateBeforeCall(
        MosaicIds mosaicIds, final ApiCallback _callback) throws ApiException {

        // verify the required parameter 'mosaicIds' is set
        if (mosaicIds == null) {
            throw new ApiException(
                "Missing the required parameter 'mosaicIds' when calling getMosaics(Async)");
        }

        okhttp3.Call localVarCall = getMosaicsCall(mosaicIds, _callback);
        return localVarCall;
    }

    /**
     * Get mosaics information for an array of mosaics Gets an array of mosaic definition.
     *
     * @param mosaicIds (required)
     * @return List&lt;MosaicInfoDTO&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     * response body
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> success </td><td>  -  </td></tr>
     * <tr><td> 400 </td><td> invalid content </td><td>  -  </td></tr>
     * <tr><td> 409 </td><td> invalid argument </td><td>  -  </td></tr>
     * </table>
     */
    public List<MosaicInfoDTO> getMosaics(MosaicIds mosaicIds) throws ApiException {
        ApiResponse<List<MosaicInfoDTO>> localVarResp = getMosaicsWithHttpInfo(mosaicIds);
        return localVarResp.getData();
    }

    /**
     * Get mosaics information for an array of mosaics Gets an array of mosaic definition.
     *
     * @param mosaicIds (required)
     * @return ApiResponse&lt;List&lt;MosaicInfoDTO&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     * response body
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> success </td><td>  -  </td></tr>
     * <tr><td> 400 </td><td> invalid content </td><td>  -  </td></tr>
     * <tr><td> 409 </td><td> invalid argument </td><td>  -  </td></tr>
     * </table>
     */
    public ApiResponse<List<MosaicInfoDTO>> getMosaicsWithHttpInfo(MosaicIds mosaicIds)
        throws ApiException {
        okhttp3.Call localVarCall = getMosaicsValidateBeforeCall(mosaicIds, null);
        Type localVarReturnType = new TypeToken<List<MosaicInfoDTO>>() {
        }.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get mosaics information for an array of mosaics (asynchronously) Gets an array of mosaic
     * definition.
     *
     * @param mosaicIds (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body
     * object
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> success </td><td>  -  </td></tr>
     * <tr><td> 400 </td><td> invalid content </td><td>  -  </td></tr>
     * <tr><td> 409 </td><td> invalid argument </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call getMosaicsAsync(
        MosaicIds mosaicIds, final ApiCallback<List<MosaicInfoDTO>> _callback) throws ApiException {

        okhttp3.Call localVarCall = getMosaicsValidateBeforeCall(mosaicIds, _callback);
        Type localVarReturnType = new TypeToken<List<MosaicInfoDTO>>() {
        }.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }

    /**
     * Build call for getMosaicsNames
     *
     * @param mosaicIds (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> success </td><td>  -  </td></tr>
     * <tr><td> 400 </td><td> invalid content </td><td>  -  </td></tr>
     * <tr><td> 409 </td><td> invalid argument </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call getMosaicsNamesCall(MosaicIds mosaicIds, final ApiCallback _callback)
        throws ApiException {
        Object localVarPostBody = mosaicIds;

        // create path and map variables
        String localVarPath = "/mosaic/names";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        final String[] localVarAccepts = {"application/json"};
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {};

        final String localVarContentType =
            localVarApiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        String[] localVarAuthNames = new String[]{};
        return localVarApiClient.buildCall(
            localVarPath,
            "POST",
            localVarQueryParams,
            localVarCollectionQueryParams,
            localVarPostBody,
            localVarHeaderParams,
            localVarFormParams,
            localVarAuthNames,
            _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getMosaicsNamesValidateBeforeCall(
        MosaicIds mosaicIds, final ApiCallback _callback) throws ApiException {

        // verify the required parameter 'mosaicIds' is set
        if (mosaicIds == null) {
            throw new ApiException(
                "Missing the required parameter 'mosaicIds' when calling getMosaicsNames(Async)");
        }

        okhttp3.Call localVarCall = getMosaicsNamesCall(mosaicIds, _callback);
        return localVarCall;
    }

    /**
     * Get readable names for a set of mosaics Returns friendly names for mosaics.
     *
     * @param mosaicIds (required)
     * @return List&lt;MosaicNamesDTO&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     * response body
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> success </td><td>  -  </td></tr>
     * <tr><td> 400 </td><td> invalid content </td><td>  -  </td></tr>
     * <tr><td> 409 </td><td> invalid argument </td><td>  -  </td></tr>
     * </table>
     */
    public List<MosaicNamesDTO> getMosaicsNames(MosaicIds mosaicIds) throws ApiException {
        ApiResponse<List<MosaicNamesDTO>> localVarResp = getMosaicsNamesWithHttpInfo(mosaicIds);
        return localVarResp.getData();
    }

    /**
     * Get readable names for a set of mosaics Returns friendly names for mosaics.
     *
     * @param mosaicIds (required)
     * @return ApiResponse&lt;List&lt;MosaicNamesDTO&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the
     * response body
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> success </td><td>  -  </td></tr>
     * <tr><td> 400 </td><td> invalid content </td><td>  -  </td></tr>
     * <tr><td> 409 </td><td> invalid argument </td><td>  -  </td></tr>
     * </table>
     */
    public ApiResponse<List<MosaicNamesDTO>> getMosaicsNamesWithHttpInfo(MosaicIds mosaicIds)
        throws ApiException {
        okhttp3.Call localVarCall = getMosaicsNamesValidateBeforeCall(mosaicIds, null);
        Type localVarReturnType = new TypeToken<List<MosaicNamesDTO>>() {
        }.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get readable names for a set of mosaics (asynchronously) Returns friendly names for mosaics.
     *
     * @param mosaicIds (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body
     * object
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> success </td><td>  -  </td></tr>
     * <tr><td> 400 </td><td> invalid content </td><td>  -  </td></tr>
     * <tr><td> 409 </td><td> invalid argument </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call getMosaicsNamesAsync(
        MosaicIds mosaicIds, final ApiCallback<List<MosaicNamesDTO>> _callback)
        throws ApiException {

        okhttp3.Call localVarCall = getMosaicsNamesValidateBeforeCall(mosaicIds, _callback);
        Type localVarReturnType = new TypeToken<List<MosaicNamesDTO>>() {
        }.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
