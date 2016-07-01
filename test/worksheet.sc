import services.LoginService
import utils.Utils

val encode = Utils.encryptPassword("11111")

Utils.validatePassword("11111", encode)