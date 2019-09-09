/*
 * Copyright 2019. NEM
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package io.nem.core.utils;

import io.nem.sdk.model.account.Address;
import io.nem.sdk.model.mosaic.MosaicId;
import io.nem.sdk.model.namespace.NamespaceId;
import java.math.BigInteger;

/**
 * Utility class for mappers.
 */
public class MapperUtils {

    /**
     * Private Contructors.
     */
    private MapperUtils() {

    }

    /**
     * Creates a {@link NamespaceId} from the provided hex number.
     *
     * @param hex the hex string with the id
     * @return a namespace id from the hex number or null if the hex is null
     */
    public static NamespaceId toNamespaceId(String hex) {
        return hex == null ? null : NamespaceId.createFromId(fromHex(hex));
    }

    /**
     * Creates a {@link MosaicId} from the provided hex number.
     *
     * @param hex the hex string with the id
     * @return a {@link MosaicId} from the hex number or null if the hex is null
     */
    public static MosaicId toMosaicId(String hex) {
        return hex == null ? null : new MosaicId(fromHex(hex));
    }

    /**
     * Creates a {@link Address} from the provided raw address.
     *
     * @param rawAddress the rawAddress
     * @return a {@link Address} from the raw address or null if the parameter is null
     */
    public static Address toAddress(String rawAddress) {
        return rawAddress != null ? Address.createFromRawAddress(rawAddress) : null;
    }

    /**
     * Creates a {@link BigInteger} from the provided hex number.
     *
     * @param hex the hex string with the id
     * @return a {@link BigInteger} from the hex number or null if the hex is null
     */
    public static BigInteger fromHex(String hex) {
        return new BigInteger(hex, 16);
    }
}
