package com.example.csvimport.data.util

import com.example.csvimport.data.db.AddressEntity
import org.apache.commons.csv.CSVFormat
import org.koin.core.annotation.Singleton
import java.io.InputStream
import java.io.InputStreamReader

@Singleton
class AddressCsvParser {
    //TODO: unparse method

    fun parse(input: InputStream): List<AddressEntity>{
        //
        val reader = InputStreamReader(input, Charsets.UTF_8)
        val format = CSVFormat.DEFAULT
            .builder()
            .setHeader()
            .setSkipHeaderRecord(true)
            .setTrim(true)
            .get()

        //
        val records = format.parse(reader)

        return records.map{ r ->
            AddressEntity(
                addressId = r.get("AddressID").toInt(),
                addressLine1 = r.get("AddressLine1"),
                addressLine2 = r.get("AddressLine2").takeIf { it.isNotBlank() },
                city = r.get("City"),
                stateProvince = r.get("StateProvince"),
                countryRegion = r.get("CountryRegion"),
                postalCode = r.get("PostalCode"),
                rowguid = r.get("Rowguid"),
                modifiedDate = r.get("ModifiedDate")
            )
        }
    }
}