# Generated by Django 4.0.3 on 2022-03-23 14:22

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('savekerala', '0012_cases_kitrequest_alter_cases_casestatus_and_more'),
    ]

    operations = [
        migrations.AddField(
            model_name='cases',
            name='casedate',
            field=models.CharField(default='', max_length=100, verbose_name='casedate'),
        ),
    ]