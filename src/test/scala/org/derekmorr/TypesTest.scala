package org.derekmorr

import eu.timepit.refined.auto._
import org.derekmorr.types.util.{dns, ip, safe, uint}

class TypesTest extends BaseTest {


    "Not compile invalid types" when {
        "it's an IPv4 address" in {
            """val noOk = ip("999.999.999.999")""" mustNot compile
        }

        "it's an IPv6 address" in {
            """val nope = ip("2001:db8::hello")""" mustNot compile
        }

        "it's DNS" in {
            """val noDns = dns("jjjj.,<>")""" mustNot compile
        }

        "it's an unsigned integer" in {
            """val noInt = uint("hello")""" mustNot compile
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

        "it's an unsigned integer" in {
            // maxint + 1
            """val j = uint("2147483648")""" must compile
        }
    }

}
