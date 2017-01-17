package org.derekmorr.types

import java.net.InetAddress

import com.google.common.net.InetAddresses
import eu.timepit.refined.api.Refined
import org.derekmorr.net.net.IP


package object util {
  def ip(s: String Refined IP): InetAddress =
    InetAddresses.forString(s.value)
}
