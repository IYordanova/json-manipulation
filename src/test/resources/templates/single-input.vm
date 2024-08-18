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
            "currency": "${details.currency}"
        }<#if details_has_next>,</#if>
    </#list>
    ],
    "vehicles": [
<#list vehicles as vehicle >
    {
        "manufacturer": "${vehicle.manufacturer}",
        "model": "${vehicle.model}",
        "name": "${vehicle.name}",
        "type": "${vehicle.type}"
    }<#if vehicle_has_next>,</#if>
</#list>
     ],
    "lastUpdated": "${last_updated }",
    "createdAt": "${created_at }"
}