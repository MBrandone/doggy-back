package doggy.back.infra.doggies

class DoggyNotFoundException(trigramme: String) : Exception("Gros, aucun doggy n'a le trigramme $trigramme")

