# Generated by Django 4.0.3 on 2022-03-29 15:24

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('savekerala', '0017_alter_servicerequest_public'),
    ]

    operations = [
        migrations.AlterField(
            model_name='public',
            name='addharcopy',
            field=models.FileField(upload_to='aadhaar/', verbose_name='addharcopy'),
        ),
        migrations.AlterField(
            model_name='public',
            name='pubphoto',
            field=models.FileField(upload_to='photo/', verbose_name='pubphoto'),
        ),
    ]
