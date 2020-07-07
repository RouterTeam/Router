package com.ifenghui.apilibrary.api.utils

import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * https 证书工具
 */
class SSLContextUtil {
    companion object {
        /**
         * 如果不需要https证书.(NoHttp已经修补了系统的SecureRandom的bug)。
         */
        fun getDefaultSLLContext(): SSLContext? {
            var sslContext: SSLContext? = null
            try {
                sslContext = SSLContext.getInstance("TLS")
                sslContext.init(
                    null,
                    arrayOf(trustManagers),
                    SecureRandom()
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return sslContext
        }

        /**
         * 信任管理器
         */
        private val trustManagers: TrustManager =
            object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<X509Certificate>,
                    authType: String
                ) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<X509Certificate>,
                    authType: String
                ) {
                }

                override fun getAcceptedIssuers(): Array<X509Certificate?> {
                    return arrayOfNulls(0)
                }
            }

        /**
         * 域名验证
         */
        val HOSTNAME_VERIFIER =
            HostnameVerifier { hostname, session -> true }
    }
}