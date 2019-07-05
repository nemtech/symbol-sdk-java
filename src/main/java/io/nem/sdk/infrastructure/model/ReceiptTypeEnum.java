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

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

/**
 * The type of the receipt: * 0x134D (4941 decimal) - Mosaic_Rental_Fee. * 0x124E (4686 decimal) -
 * Namespace_Rental_Fee. * 0x2143 (8515 decimal) - Harvest_Fee. * 0x2248 (8776 decimal) -
 * LockHash_Completed. * 0x2348 (9032 decimal) - LockHash_Expired. * 0x2252 (8786 decimal) -
 * LockSecret_Completed. * 0x2352 (9042 decimal) - LockSecret_Expired. * 0x3148 (12616 decimal) -
 * LockHash_Created. * 0x3152 (12626 decimal) - LockSecret_Created. * 0x414D (16717 decimal) -
 * Mosaic_Expired. * 0x414E (16718 decimal) - Namespace_Expired. * 0x5143 (20803 decimal) -
 * Inflation. * 0xE134 (57652 decimal) - Transaction_Group. * 0xF143 (61763 decimal) -
 * Address_Alias_Resolution. * 0xF243 (62019 decimal) - Mosaic_Alias_Resolution.
 */
@JsonAdapter(ReceiptTypeEnum.Adapter.class)
public enum ReceiptTypeEnum {
    NUMBER_4685(4685),

    NUMBER_4941(4941),

    NUMBER_4686(4686),

    NUMBER_8515(8515),

    NUMBER_8776(8776),

    NUMBER_9042(9042),

    NUMBER_12616(12616),

    NUMBER_12626(12626),

    NUMBER_16717(16717),

    NUMBER_16718(16718),

    NUMBER_20803(20803),

    NUMBER_57652(57652),

    NUMBER_61763(61763),

    NUMBER_62019(62019);

    private Integer value;

    ReceiptTypeEnum(Integer value) {
        this.value = value;
    }

    public static ReceiptTypeEnum fromValue(Integer value) {
        for (ReceiptTypeEnum b : ReceiptTypeEnum.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static class Adapter extends TypeAdapter<ReceiptTypeEnum> {

        @Override
        public void write(final JsonWriter jsonWriter, final ReceiptTypeEnum enumeration)
            throws IOException {
            jsonWriter.value(enumeration.getValue());
        }

        @Override
        public ReceiptTypeEnum read(final JsonReader jsonReader) throws IOException {
            Integer value = jsonReader.nextInt();
            return ReceiptTypeEnum.fromValue(value);
        }
    }
}
