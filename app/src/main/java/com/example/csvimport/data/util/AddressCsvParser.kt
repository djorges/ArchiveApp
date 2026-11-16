package com.example.csvimport.data.util

import com.example.csvimport.data.db.AddressEntity
import org.apache.commons.csv.CSVFormat
import org.koin.core.annotation.Singleton
import java.io.InputStream
import java.io.InputStreamReader

@Singleton
class AddressCsvParser {

    private val requiredHeaders = setOf(
        "AddressID",
        "AddressLine1",
        "AddressLine2",
        "City",
        "StateProvince",
        "CountryRegion",
        "PostalCode",
        "Rowguid",
        "ModifiedDate"
    )

    private val inputFormat = CSVFormat.DEFAULT.builder()
        .setHeader()
        .setSkipHeaderRecord(true)
        .setTrim(true)
        .get()

    fun parse(input: InputStream): List<AddressEntity> {
        return input.bufferedReader(Charsets.UTF_8).use { reader ->

            inputFormat.parse(reader).use { records ->

                validateHeaders(records.headerNames.toSet())

                records.map { record ->
                    validateRecord(record)
                    record.toAddressEntity()
                }
            }
        }
    }

    private fun validateHeaders(headers: Set<String>) {
        val missing = requiredHeaders - headers

        require(missing.isEmpty()) {
            "Faltan columnas en el CSV: $missing"
        }
    }

    private fun validateRecord(record: CSVRecord) {
        require(record.isConsistent) {
            "Cantidad de columnas incorrecta en fila ${record.recordNumber}"
        }

        require(record.get("AddressID").toIntOrNull() != null) {
            "AddressID inválido en fila ${record.recordNumber}"
        }
    }

    private fun CSVRecord.toAddressEntity(): AddressEntity {
        return AddressEntity(
            addressId = get("AddressID").toInt(),
            addressLine1 = get("AddressLine1"),
            addressLine2 = get("AddressLine2")
                .takeIf { it.isNotBlank() },
            city = get("City"),
            stateProvince = get("StateProvince"),
            countryRegion = get("CountryRegion"),
            postalCode = get("PostalCode"),
            rowguid = get("Rowguid"),
            modifiedDate = get("ModifiedDate")
        )
    }
}
