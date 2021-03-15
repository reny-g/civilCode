/**
 * 数据值校验工具类
 */
var checkService = {
    // 不校验
    none: function () {
        return true;
    },

    //非空校验
    isEmpty:function (str) {
        if (str == null || str == "") return false;
    },
    // 只能输入数字[0-9]
    isDigits: function (str) {
        if (str == null || str == "") return true;
        var reg = /^\d+$/;
        return reg.test(str);
    },

    // 匹配english
    isEnglish: function (str) {
        if (str == null || str == "") return true;
        var reg = /^[A-Za-z]+$/;
        return reg.test(str);
    },

    // 匹配integer(包含正负)
    isInteger: function (str) {
        if (str == null || str == "") return true;
        var reg = /^[-\+]?\d+$/;
        return reg.test(str);
    },

    // 匹配汉字
    isChinese: function (str) {
        if (str == null || str == "") return true;
        var reg = /^[\u4e00-\u9fa5\s]+$/;
        return reg.test(str);
    },

    // 匹配中文(双字节字符,包括汉字和符号)
    isChineseChar: function (str) {
        if (str == null || str == "") return true;
        var reg = /^[\u0391-\uFFE5\s]+$/;
        return reg.test(str);
    },

    // 匹配URL
    isUrl: function (str) {
        if (str == null || str == "") return true;
        var reg = /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\’:+!]*([^<>\"])*$/;
        return reg.test(str);
    },

    // 字符验证，只能包含中文、英文、数字、下划线、空格。
    stringCheck: function (str) {
        if (str == null || str == "") return true;
        var reg = /^[a-zA-Z0-9\u4e00-\u9fa5_ ]+$/;
        return reg.test(str);
    },

    //字符长度校验（最长64位）
    stringLengthCheck: function (str, length) {
        if (str == null || str == "") return true;
        length = length || 64;
        if (str.length > length) return false;
        return true;
    },
    //IP格式验证
    isIP: function (str) {
        if (str == null || str == "") return true;
        var reg = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
        return reg.test(str);
    }
};