# Generated by Django 4.0.3 on 2022-03-28 11:36

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('savekerala', '0015_bank_balance'),
    ]

    operations = [
        migrations.AddField(
            model_name='servicerequest',
            name='worker',
            field=models.ForeignKey(null=True, on_delete=django.db.models.deletion.CASCADE, to='savekerala.worker'),
        ),
    ]
