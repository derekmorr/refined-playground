package org.derekmorr

import eu.timepit.refined.auto._
import org.derekmorr.types.VlanId
import org.derekmorr.types.util.{dns, ip, ipv4, ipv6, safe, uint}

class TypesTest extends BaseTest {


  "Not compile invalid types" when {
    "it's an IPv4 address" in {
      """val noOk = ipv4("999.999.999.999")""" mustNot compile
    }

    "it's an IPv6 address" in {
      """val nope = ipv6("2001:db8::hello")""" mustNot compile
    }

    "it's an IPv4 address but we requested ipv6" in {
      """val nope = ipv6("192.168.0.1")""" mustNot compile
    }

    "it's an IPv6 address, but we requested IPv4" in {
      """val nope = ipv4("2001:db8::f00")""" mustNot compile
    }

    "it's DNS" in {
      """val noDns = dns("jjjj.,<>")""" mustNot compile
    }

    "it's an unsigned integer" in {
      """val noInt = uint("hello")""" mustNot compile
    }

    "it's a vlan id" in {
      """val noVlan: VlanId = 9999""" mustNot compile
    }

    "it's a safe string" in {
      """val notSafe: = safe("'; rm -rf /")""" mustNot compile
    }
  }

  "Compile valid types" when {
    "it's an IPv4 address" in {
      """val ipv4Addr = ipv4("192.168.0.1")""" must compile
    }

    "it's an IPv6 address" in {
      """val ipv6Addr = ipv6("2001:db8::abcd:123")""" must compile
    }

    "it's a DNS name" in {
      """val hostname = dns("foo.bar.org.blah")""" must compile
    }

    "it's an unsigned integer" in {
      // maxint + 1
      """val j = uint("2147483648")""" must compile
    }

    "it's a vlan id" in {
      """val vlan: VlanId = 137""" must compile
    }

    "it's a safe string" in {
      """val safeStr = safe("this is (a safe) string1234")""" must compile
    }
  }

}
