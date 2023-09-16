# Test1

при вводе "/symbols-counter?input=aaaaabcccc":
вывод:
{
    "a": 5,
    "c": 4,
    "b": 1
}

если input пустой ("/symbols-counter?input="):
{
    "errors": ["must not be blank"]
}

параметр не введен ("/symbols-counter"):
{
    "errors": ["input parameter is missing"]
}
