# Generated by Django 4.0.3 on 2022-03-27 05:51

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('savekerala', '0014_bank_alter_medicalshop_licensecpy_order_item'),
    ]

    operations = [
        migrations.AddField(
            model_name='bank',
            name='balance',
            field=models.IntegerField(default=29000),
            preserve_default=False,
        ),
    ]
