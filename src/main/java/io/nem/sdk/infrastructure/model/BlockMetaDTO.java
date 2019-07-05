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

package io.nem.sdk.infrastructure.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * BlockMetaDTO
 */
@javax.annotation.Generated(
    value = "org.openapitools.codegen.languages.JavaClientCodegen",
    date = "2019-06-20T19:56:23.892+01:00[Europe/London]")
public class BlockMetaDTO {

    public static final String SERIALIZED_NAME_HASH = "hash";
    public static final String SERIALIZED_NAME_GENERATION_HASH = "generationHash";
    public static final String SERIALIZED_NAME_SUB_CACHE_MERKLE_ROOTS = "subCacheMerkleRoots";
    public static final String SERIALIZED_NAME_TOTAL_FEE = "totalFee";
    public static final String SERIALIZED_NAME_NUM_TRANSACTIONS = "numTransactions";
    public static final String SERIALIZED_NAME_NUM_STATEMENTS = "numStatements";
    @SerializedName(SERIALIZED_NAME_HASH)
    private String hash;
    @SerializedName(SERIALIZED_NAME_GENERATION_HASH)
    private String generationHash;
    @SerializedName(SERIALIZED_NAME_SUB_CACHE_MERKLE_ROOTS)
    private List<String> subCacheMerkleRoots = new ArrayList<String>();
    @SerializedName(SERIALIZED_NAME_TOTAL_FEE)
    private List<Integer> totalFee = new ArrayList<Integer>();
    @SerializedName(SERIALIZED_NAME_NUM_TRANSACTIONS)
    private Integer numTransactions;
    @SerializedName(SERIALIZED_NAME_NUM_STATEMENTS)
    private Integer numStatements;

    public BlockMetaDTO hash(String hash) {
        this.hash = hash;
        return this;
    }

    /**
     * Get hash
     *
     * @return hash
     */
    @ApiModelProperty(
        example = "C8FC3FB54FDDFBCE0E8C71224990124E4EEC5AD5D30E592EDFA9524669A23810",
        required = true,
        value = "")
    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public BlockMetaDTO generationHash(String generationHash) {
        this.generationHash = generationHash;
        return this;
    }

    /**
     * Get generationHash
     *
     * @return generationHash
     */
    @ApiModelProperty(
        example = "876614A913BAA95E64451290BB3BAD476625F0EB27CA7953EC7F802CC9FBB18D",
        required = true,
        value = "")
    public String getGenerationHash() {
        return generationHash;
    }

    public void setGenerationHash(String generationHash) {
        this.generationHash = generationHash;
    }

    public BlockMetaDTO subCacheMerkleRoots(List<String> subCacheMerkleRoots) {
        this.subCacheMerkleRoots = subCacheMerkleRoots;
        return this;
    }

    public BlockMetaDTO addSubCacheMerkleRootsItem(String subCacheMerkleRootsItem) {
        this.subCacheMerkleRoots.add(subCacheMerkleRootsItem);
        return this;
    }

    /**
     * Get subCacheMerkleRoots
     *
     * @return subCacheMerkleRoots
     */
    @ApiModelProperty(
        example =
            "[782451A35BCE10E5DCB2BBB5A1A8C067F8673DC65EB6BAAE3EE533044BF742CC, 646AE657A9717BECF338279C45613C8F64FB69572E22F4CAE2A36097C2190F02, 65D0CDEB6F8C29A9B164C64FFBC7AD97DF2BA407FE868E1BBC11983DBFF3FB5A, 0, 0, 0, 0]",
        required = true,
        value = "")
    public List<String> getSubCacheMerkleRoots() {
        return subCacheMerkleRoots;
    }

    public void setSubCacheMerkleRoots(List<String> subCacheMerkleRoots) {
        this.subCacheMerkleRoots = subCacheMerkleRoots;
    }

    public BlockMetaDTO totalFee(List<Integer> totalFee) {
        this.totalFee = totalFee;
        return this;
    }

    public BlockMetaDTO addTotalFeeItem(Integer totalFeeItem) {
        this.totalFee.add(totalFeeItem);
        return this;
    }

    /**
     * Get totalFee
     *
     * @return totalFee
     */
    @ApiModelProperty(example = "[lower, higher]", required = true, value = "")
    public List<Integer> getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(List<Integer> totalFee) {
        this.totalFee = totalFee;
    }

    public BlockMetaDTO numTransactions(Integer numTransactions) {
        this.numTransactions = numTransactions;
        return this;
    }

    /**
     * Get numTransactions
     *
     * @return numTransactions
     */
    @ApiModelProperty(example = "0", required = true, value = "")
    public Integer getNumTransactions() {
        return numTransactions;
    }

    public void setNumTransactions(Integer numTransactions) {
        this.numTransactions = numTransactions;
    }

    public BlockMetaDTO numStatements(Integer numStatements) {
        this.numStatements = numStatements;
        return this;
    }

    /**
     * Get numStatements
     *
     * @return numStatements
     */
    @ApiModelProperty(example = "1", value = "")
    public Integer getNumStatements() {
        return numStatements;
    }

    public void setNumStatements(Integer numStatements) {
        this.numStatements = numStatements;
    }

    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BlockMetaDTO blockMetaDTO = (BlockMetaDTO) o;
        return Objects.equals(this.hash, blockMetaDTO.hash)
            && Objects.equals(this.generationHash, blockMetaDTO.generationHash)
            && Objects.equals(this.subCacheMerkleRoots, blockMetaDTO.subCacheMerkleRoots)
            && Objects.equals(this.totalFee, blockMetaDTO.totalFee)
            && Objects.equals(this.numTransactions, blockMetaDTO.numTransactions)
            && Objects.equals(this.numStatements, blockMetaDTO.numStatements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            hash, generationHash, subCacheMerkleRoots, totalFee, numTransactions, numStatements);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BlockMetaDTO {\n");
        sb.append("    hash: ").append(toIndentedString(hash)).append("\n");
        sb.append("    generationHash: ").append(toIndentedString(generationHash)).append("\n");
        sb.append("    subCacheMerkleRoots: ")
            .append(toIndentedString(subCacheMerkleRoots))
            .append("\n");
        sb.append("    totalFee: ").append(toIndentedString(totalFee)).append("\n");
        sb.append("    numTransactions: ").append(toIndentedString(numTransactions)).append("\n");
        sb.append("    numStatements: ").append(toIndentedString(numStatements)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces (except the first
     * line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
