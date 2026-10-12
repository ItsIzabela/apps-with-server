from django.db import models

class Chat(models.Model):
    name = models.CharField(max_length=16)
    message = models.TextField()

    def __str__(self):
        return self.name
