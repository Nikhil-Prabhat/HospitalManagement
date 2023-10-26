#Provider
provider "aws" {
  region     = "ap-south-1"
  access_key = "AKIAUV66CJ23OJ73ZNUB"
  secret_key = "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"
}

# Database 
resource "aws_db_instance" "hm-db" {
  identifier = "hmdb"
  allocated_storage   = 10
  db_name             = "hmdb"
  engine              = "postgres"
  instance_class      = "db.t3.micro"
  username            = "root"
  password            = "adminqwerty"
  skip_final_snapshot = true
  publicly_accessible = true
}

# SNS
resource "aws_sns_topic" "HM_Notification_Topic" {
  name = "HM_Notification_Topic"
}

# SNS Subscription
resource "aws_sns_topic_subscription" "HM-Notification-Topic-Email-Subscription" {
  topic_arn = aws_sns_topic.HM_Notification_Topic.arn
  protocol  = "email"
  endpoint  = "strangernikhilprabhat98@gmail.com"
}



