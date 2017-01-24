package org.derekmorr

import eu.timepit.refined.auto._
import org.derekmorr.types.util.{dns, ip, safe}

class TypesTest extends BaseTest {


    "Not compile invalid types" when {
        "it's an IPv4 address" in {
            """val noOk = ip("999.999.999.999")""" mustNot compile
        }

        "it's an IPv6 address" in {
            """val nope = ip("2001:db8::hello")""" mustNot compile
        }

        "it's DNS" in {
            """dns("jjjj.,<>")""" mustNot compile
        }
    }

    "Compile valid types" when {
        "it's an IPv4 address" in {
            """val ipv4 = ip("192.168.0.1")""" must compile
        }

        "it's an IPv6 address" in {
            """val ipv6 = ip("2001:db8::abcd:123")""" must compile
        }

        "it's a DNS name" in {
            """val hostname = dns("foo.bar.org.blah")""" must compile
        }
    }

}
