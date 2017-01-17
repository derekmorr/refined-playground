package org.derekmorr.net

import java.net.InetAddress

import com.google.common.net.InetAddresses
import eu.timepit.refined.api.Validate
import org.derekmorr.net.net.IP

object net extends NetValidate {
  final case class IP()
}

private[net] trait NetValidate {


  implicit def ipValidate: Validate.Plain[String, IP] =
    Validate.fromPartial(InetAddresses.forString, "IP Address", IP())
}
