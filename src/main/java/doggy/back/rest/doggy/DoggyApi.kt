package doggy.back.rest.doggy

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "Doggy")
data class DoggyApi(
    @ApiModelProperty(value = "Le trigramme du doggy", example = "FHI")
    val trigramme: String,
    @ApiModelProperty(value = "Le nom (de famille) du doggy", example = "Zidane")
    val nom: String,
    @ApiModelProperty(value = "Le prénom du doggy", example = "Raymonde")
    val prenom: String,
    @ApiModelProperty(
        value = "Le surnom du doggy (si il est pas bon, ben c'est la faute de brondon)",
        example = "La lime"
    )
    val surnom: String,
    @ApiModelProperty(value = "La plus belle photo du doggy", example = "Ceci est une photo")
    val photo: String,
    @ApiModelProperty(value = "La tribu du doggy", example = "CONEX")
    val tribu: String,
    @ApiModelProperty(value = "Le mail du doggy", example = "trololol@octo.com")
    val email: String,
    @ApiModelProperty(value = "Comment tu peux reconnaitre le doggy", example = "Le rire infâme de brondon")
    val signeParticulier: String
)
