{
    "fullName": "${full_name}",
    "uid": "${uid}",
    "contactDetails": {
        "address": {
            "street": "${address.street}",
            "city": "${address.city}"
        },
        "emailAddress": "${email}",
        "phoneNumber": "${phone}"
    },
    "paymentDetails": [
    <#list payment_details as details>
        {
            "IBAN": "${details.iban}",
            "cardDetails": {
                "number": "${details.credit_card_number}",
                "CVV": "${details.credit_card_cvv}"
            },
            "currency": "${mapCurrency(details.currency)}"
        }<#if details_has_next>,</#if>
    </#list>
    ],
    "vehicles": [
<#list vehicles as vehicle >
    "${formatVehicle(vehicle.manufacturer, vehicle.model, vehicle.name, vehicle.type)}"
    <#if vehicle_has_next>,</#if>
</#list>
     ],
    "lastUpdated": "${timeNow() }",
    "createdAt": "${created_at }"
}