/*
 * Copyright 2020 NEM
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

package io.nem.symbol.sdk.model.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.nem.symbol.sdk.model.network.NetworkType;
import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

class AddressTest {

    private static Address generateAddress(NetworkType networkType) {
        return Account.generateNewAccount(networkType).getAddress();
    }

    private static Stream<Arguments> provider() {
        return Arrays.stream(NetworkType.values())
            .map(networkType -> Arguments.of(generateAddress(networkType), networkType));
    }

    private static Stream<Arguments> assertExceptionProvider() {
        return Arrays.stream(NetworkType.values()).map(networkType -> Arguments.of(generateAddress(networkType).plain(),
            Arrays.stream(NetworkType.values()).filter(n -> n != networkType).findFirst().orElse(null)));
    }


    @Test
    void testAddressCreation() {
        Address address =
            new Address("SDGLFW-DSHILT-IUHGIB-H5UGX2-VYF5VN-JEKCCD-BR2", NetworkType.MIJIN_TEST);
        assertEquals("SDGLFWDSHILTIUHGIBH5UGX2VYF5VNJEKCCDBR2", address.plain());
    }

    @Test
    void testAddressWithSpacesCreation() {
        Address address =
            new Address(" SDGLFW-DSHILT-IUHGIB-H5UGX2-VYF5VN-JEKCCD-BR2 ", NetworkType.MIJIN_TEST);
        assertEquals("SDGLFWDSHILTIUHGIBH5UGX2VYF5VNJEKCCDBR2", address.plain());
    }

    @Test
    void testLowerCaseAddressCreation() {
        Address address =
            new Address("sdglfw-dshilt-iuhgib-h5ugx2-vyf5vn-jekccd-br2", NetworkType.MIJIN_TEST);
        assertEquals("SDGLFWDSHILTIUHGIBH5UGX2VYF5VNJEKCCDBR2", address.plain());
    }

    @Test
    void shouldCreateFromEncoded() {
        String encoded = "6826D27E1D0A26CA4E316F901E23E55C8711DB20DF250DEF";
        Address address = Address
            .createFromEncoded(encoded);
        assertEquals(encoded, address.encoded());
        assertEquals(encoded, address.encoded(NetworkType.MIJIN_TEST).toUpperCase());
    }

    @Test
    void shouldCreateFromEncodedFailWhenInvalid() {
        Assertions.assertEquals(
            "invalid! could not be decoded. DecoderException: Illegal hexadecimal character i at index 0",
            Assertions.assertThrows(IllegalArgumentException.class,
                () -> Address.createFromEncoded("invalid!")).getMessage());
    }


    @Test
    void addressInPrettyFormat() {
        Address address =
            new Address("SDRDGF-TDLLCB-67D4HP-GIMIHP-NSRYRJ-RT7DOB-GWZ", NetworkType.MIJIN_TEST);
        assertEquals("SDRDGF-TDLLCB-67D4HP-GIMIHP-NSRYRJ-RT7DOB-GWZ", address.pretty());
    }

    @Test
    void createFromEncodedSameResult() {
        Address address2 = Address.createFromRawAddress("NAR3W7B4BCOZSZMFIZRYB3N5YGOUSWIYJCJ6HDF");
        Address address1 = Address.createFromRawAddress("NAR3W7B4BCOZSZMFIZRYB3N5YGOUSWIYJCJ6HDA");
        assertEquals(address1.encoded(), address2.encoded());
        assertEquals(address1, Address.createFromEncoded(address1.encoded()));
        assertNotEquals(address1.plain(), address2.plain());
    }

    @Test
    void createFromEncoded() {
        Address address2 = Address.createFromRawAddress("NAR3W7B4BCOZSZMFIZRYB3N5YGOUSWIYJCJ6HDA");
        Address address1 = Address.createFromEncoded("6823BB7C3C089D996585466380EDBDC19D4959184893E38C");
        assertEquals(address1.plain(), address2.plain());
    }

    @Test
    void createFromEncodedDuplicated() {
        Address address2 = Address.createFromRawAddress("NAR3W7B4BCOZSZMFIZRYB3N5YGOUSWIYJCJ6HDF");
        Address address1 = Address.createFromEncoded("6823BB7C3C089D996585466380EDBDC19D4959184893E38C");
        assertEquals("NAR3W7B4BCOZSZMFIZRYB3N5YGOUSWIYJCJ6HDF", address2.plain());
        assertEquals("NAR3W7B4BCOZSZMFIZRYB3N5YGOUSWIYJCJ6HDA", address1.plain());
    }


    @Test
    void equality() {
        Address address1 =
            new Address("SDRDGF-TDLLCB-67D4HP-GIMIHP-NSRYRJ-RT7DOB-GWZ", NetworkType.MIJIN_TEST);
        Address address2 =
            new Address("SDRDGFTDLLCB67D4HPGIMIHPNSRYRJRT7DOBGWZ", NetworkType.MIJIN_TEST);
        assertEquals(address1, address2);
    }

    @Test
    void noEquality() {
        Address address1 = generateAddress(NetworkType.MIJIN_TEST);
        Address address2 = generateAddress(NetworkType.MIJIN_TEST);
        assertNotEquals(address1, address2);
        assertNotEquals("notAndAddress", address2);
    }

    @ParameterizedTest
    @MethodSource("assertExceptionProvider")
    void testThrowErrorWhenNetworkTypeIsNotTheSameAsAddress(
        String rawAddress, NetworkType networkType) {
        assertThrows(
            IllegalArgumentException.class,
            () -> {
                new Address(rawAddress, networkType);
            });
    }

    @ParameterizedTest
    @MethodSource("assertExceptionProvider")
    void shouldReturnDifferentNetworkType(
        String address, NetworkType networkType) {
        Assertions
            .assertNotEquals(networkType, Address.createFromRawAddress(address).getNetworkType());
    }

    @Test
    void createFromRawAddressShouldFailWhenInvalidSize() {
        Assertions.assertEquals("Plain address 'X' size is 1 when 39 is required", assertThrows(
            IllegalArgumentException.class,
            () -> {
                Address.createFromRawAddress("X");
            }).getMessage());
    }

    @Test
    void createFromRawAddressShouldFailWhenInvalidSuffix() {
        Assertions.assertEquals(
            "Plain address 'ADRDGFTDLLCB67D4HPGIMIHPNSRYRJRT7DOBGWZ' checksum is incorrect. Address checksum is 'DC135B' when '61DABF' is expected",
            assertThrows(
                IllegalArgumentException.class,
                () -> {
                    Address.createFromRawAddress("ADRDGF-TDLLCB-67D4HP-GIMIHP-NSRYRJ-RT7DOB-GWZ");
                }).getMessage());
    }

    @ParameterizedTest
    @EnumSource(NetworkType.class)
    void createShouldFailWhenInvalidPublicKey(NetworkType networkType) {
        Assertions.assertEquals("Public key is not valid", assertThrows(
            IllegalArgumentException.class,
            () -> Address.createFromPublicKey("InvalidPublicKey", networkType)).getMessage());
    }


    @ParameterizedTest
    @MethodSource("provider")
    void tesNetworkTypeFromRawAddress(Address paramAddress, NetworkType input) {
        Address address = new Address(paramAddress.plain(), input);
        assertEquals(input, address.getNetworkType());
        assertTrue(Address.isValidPlainAddress(address.plain()));
    }

    @ParameterizedTest
    @MethodSource("assertExceptionProvider")
    void isValidAddressWhenInvalid(String rawAddress, NetworkType networkType) {
        boolean validPlainAddress = Address.isValidPlainAddress(rawAddress);
        if (validPlainAddress) {
            Assertions.assertNotEquals(networkType,
                Address.createFromRawAddress(rawAddress).getNetworkType());
        } else {
            Assertions.assertFalse(validPlainAddress);
        }
    }


    @ParameterizedTest
    @EnumSource(NetworkType.class)
    void isValidAddressFromGeneratedPublicKey(NetworkType networkType) {
        Assertions.assertTrue(Address.isValidPlainAddress(generateAddress(networkType).plain()));
        Assertions.assertTrue(Address.isValidEncodedAddress(generateAddress(networkType).encoded()));
    }


    @Test
    void validate() {
        Assertions.assertEquals("Plain Address it nos provided", Address.validatePlainAddress(null).get());
        Assertions.assertEquals("Plain address 'ABC' size is 3 when 39 is required",
            Address.validatePlainAddress("ABC").get());
        Assertions.assertEquals(
            "Plain address 'S111GFTDLLCB67D4HPGIMIHPNSRYRJRT7DOBGGZ' is invalid. Error: IllegalArgumentException: malformed base32 string passed to getBytes",
            Address.validatePlainAddress("S111GFTDLLCB67D4HPGIMIHPNSRYRJRT7DOBGGZ").get());
        Assertions.assertEquals(
            "Plain address 'SDRDGFTDLLCB67D4HPGIMIHPNSRYRJRT7DOBGGZ' checksum is incorrect. Address checksum is 'DC131B' when 'DC135B' is expected",
            Address.validatePlainAddress("SDRDGFTDLLCB67D4HPGIMIHPNSRYRJRT7DOBGGZ").get());
    }

}
