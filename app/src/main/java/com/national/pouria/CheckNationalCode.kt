package com.national.pouria

class CheckNationalCode {

    private var checkNationalCode: CheckNationalCode? = null

    fun getService(): CheckNationalCode {
        if (checkNationalCode == null) {
            checkNationalCode = CheckNationalCode()
        }
        return checkNationalCode!!
    }

    private val notNationalCode = arrayOf(
            "0000000000",
            "1111111111",
            "2222222222",
            "3333333333",
            "4444444444",
            "5555555555",
            "6666666666",
            "7777777777",
            "8888888888",
            "9999999999"
    )

    fun checkNationalCode(nationalCode: String?): Boolean {
        if (nationalCode == null) {
            return false
        }
        if (nationalCode.isEmpty()) {
            return false
        }
        if (nationalCode.equals("")){
            return false
        }
        if (nationalCode.length != 10) {
            return false
        }
        if (!nationalCode.matches("[0-9]+".toRegex())) {
            return false
        }
        for (s in notNationalCode) {
            if (s.equals(nationalCode, ignoreCase = true)) {
                return false
            }
        }
        val nationalCodeWithoutControlDigit = nationalCode.substring(0, nationalCode.length - 1)
        val controlDigit = nationalCode.substring(nationalCode.length - 1)
        var sum = 0
        var i = 10
        for (c in nationalCodeWithoutControlDigit.toCharArray()) {
            val temp = ("" + c).toInt() * i
            i--
            sum += temp
        }
        val modBy11 = sum % 11
        return if (modBy11 < 2) {
            modBy11 == controlDigit.toInt()
        } else 11 - modBy11 == controlDigit.toInt()
    }
}


