/*
 * Copyright 2018 NEM
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

package io.nem.sdk.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.nem.sdk.model.blockchain.NetworkType;
import io.nem.sdk.model.mosaic.MosaicId;
import io.nem.sdk.model.mosaic.MosaicNonce;
import io.nem.sdk.model.mosaic.MosaicProperties;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MosaicDefinitionTransactionTest {

    @Test
    void createAMosaicCreationTransactionViaStaticConstructor() {
        MosaicDefinitionTransaction mosaicCreationTx =
            MosaicDefinitionTransaction.create(
                new Deadline(2, ChronoUnit.HOURS),
                BigInteger.ZERO,
                MosaicNonce.createFromBigInteger(new BigInteger("0")),
                new MosaicId(new BigInteger("0")),
                MosaicProperties.create(true, true, 3, BigInteger.valueOf(10)),
                NetworkType.MIJIN_TEST);

        assertEquals(NetworkType.MIJIN_TEST, mosaicCreationTx.getNetworkType());
        assertTrue(1 == mosaicCreationTx.getVersion());
        assertTrue(LocalDateTime.now().isBefore(mosaicCreationTx.getDeadline().getLocalDateTime()));
        assertEquals(BigInteger.valueOf(0), mosaicCreationTx.getFee());
        assertEquals(new BigInteger("0"), mosaicCreationTx.getMosaicId().getId());
        assertEquals(true, mosaicCreationTx.getMosaicProperties().isSupplyMutable());
        assertEquals(true, mosaicCreationTx.getMosaicProperties().isTransferable());
        assertEquals(3, mosaicCreationTx.getMosaicProperties().getDivisibility());
        assertEquals(
            BigInteger.valueOf(10), mosaicCreationTx.getMosaicProperties().getDuration().get());
    }

    @Test
    @DisplayName("Serialization")
    void serialization() {
        String expected =
            "9000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001904d4100000000000000000100000000000000000000000000000000000000010304021027000000000000";
        MosaicDefinitionTransaction mosaicDefinitionTransaction =
            MosaicDefinitionTransaction.create(
                new FakeDeadline(),
                BigInteger.ZERO,
                MosaicNonce.createFromBigInteger(new BigInteger("0")),
                new MosaicId(new BigInteger("0")),
                MosaicProperties.create(true, true, 4, BigInteger.valueOf(10000)),
                NetworkType.MIJIN_TEST);

        byte[] actual = mosaicDefinitionTransaction.generateBytes();
        assertEquals(expected, Hex.toHexString(actual));
    }
}