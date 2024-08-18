{
    "results": [
    <#list results as result>
    {
        "fullName": "${result.full_name}",
        "uid": "${result.uid}",
        "contactDetails": {
            "address": {
                "street": "${result.address.street}",
                "city": "${result.address.city}"
            },
        "emailAddress": "${result.email}",
        "phoneNumber": "${result.phone}"
        },
        "paymentDetails": [
        <#list result.payment_details as details>
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
        <#list result.vehicles as vehicle >
            {
                "manufacturer": "${vehicle.manufacturer}",
                "model": "${vehicle.model}",
                "name": "${vehicle.name}",
                "type": "${vehicle.type}"
            }<#if vehicle_has_next>,</#if>
        </#list>
        ],
        "lastUpdated": "${result.last_updated }",
        "createdAt": "${result.created_at }"
    }<#if result_has_next>,</#if>
    </#list>
    ]
}