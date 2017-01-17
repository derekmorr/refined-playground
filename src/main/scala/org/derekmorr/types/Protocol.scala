package org.derekmorr.types

import eu.timepit.refined.api.Refined
import eu.timepit.refined.collection.NonEmpty
import eu.timepit.refined.types.net.PortNumber

/**
  * Created by derek on 1/16/17.
  */
case class Protocol(name: String Refined NonEmpty,
                    port: PortNumber)
