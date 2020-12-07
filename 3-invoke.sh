#!/bin/bash
set -eo pipefail
FUNCTION=$(aws cloudformation describe-stack-resource --stack-name accumulator --logical-resource-id function --query 'StackResourceDetail.PhysicalResourceId' --output text)

PAYLOAD='[24,25,26]'

while true; do
aws lambda invoke --function-name $FUNCTION --payload $PAYLOAD out.json
cat out.json
echo ""
sleep 2
done
