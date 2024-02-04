package rest.api

def list = [[1,2],[3,4]]
print list.flatten()

println()

def list1 = [[1,2],[3,4]]
print list1.flatten().findAll{it>2}

