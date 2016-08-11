package com.bob.rules.reguralrule.model

import scala.beans.BeanProperty

/**
 * 房间
 */
case class Room(@BeanProperty var name: String)

case class Sprinkler(@BeanProperty var room: Room, @BeanProperty var on: Boolean)

case class Fire(@BeanProperty var room: Room)

class Alarm() {}