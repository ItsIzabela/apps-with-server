from django.shortcuts import render
from django.http import HttpResponse

def message(request):
    name = request.GET.get("name") or "User"
    return HttpResponse("Hello {}!".format(name))
