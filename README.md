# AWS-IoT-Assignment
REST APIs to perform CRUD operations on Equipment

1. Jar has been transfer to EC2 using FileZela desktop application.
2. Command used to run application on EC2 linux server.
    - nohup java -Xms512m -Xmx1024m -jar IoT-Assignment-1.0.jar &
 
 
 
 SWAGGER DOCUMENTATION:-
 {
	"swagger": "2.0",
	"info": {
		"description": "This page documents Equipment REST APIs version 1.0",
		"version": "API TOS",
		"title": "Equipment details API",
		"termsOfService": "Terms of service",
		"contact": {
			"name": "Vivek Mishra",
			"email": "vivekmishra22117@gmail.com"
		},
		"license": {
			"name": "License of API",
			"url": "API license URL"
		}
	},
	"host": "34.225.141.189:8181",
	"basePath": "/iot-assignment",
	"tags": [{
		"name": "equipment-data-controller",
		"description": "Equipment Data Controller"
	}],
	"paths": {
		"/equipment/": {
			"post": {
				"tags": ["equipment-data-controller"],
				"summary": "Creates a new equipment",
				"operationId": "createEquipmentUsingPOST",
				"consumes": ["application/json"],
				"produces": ["application/json"],
				"parameters": [{
					"in": "body",
					"name": "record",
					"description": "record",
					"required": true,
					"schema": {
						"$ref": "#/definitions/EquipmentRecord"
					}
				}],
				"responses": {
					"200": {
						"description": "OK",
						"schema": {
							"type": "object"
						}
					},
					"201": {
						"description": "Created"
					},
					"401": {
						"description": "Unauthorized"
					},
					"403": {
						"description": "Forbidden"
					},
					"404": {
						"description": "Not Found"
					}
				},
				"deprecated": false
			}
		},
		"/equipment/search": {
			"get": {
				"tags": ["equipment-data-controller"],
				"summary": "Get list of equipments based on count ",
				"operationId": "getEquipmentDataUsingGET",
				"produces": ["application/json"],
				"parameters": [{
					"name": "limit",
					"in": "query",
					"description": "limit",
					"required": true,
					"type": "integer",
					"format": "int32"
				}],
				"responses": {
					"200": {
						"description": "OK",
						"schema": {
							"$ref": "#/definitions/EquipmentRecord"
						}
					},
					"401": {
						"description": "Unauthorized"
					},
					"403": {
						"description": "Forbidden"
					},
					"404": {
						"description": "Not Found"
					}
				},
				"deprecated": false
			}
		},
		"/equipment/{equipmentNumber}": {
			"get": {
				"tags": ["equipment-data-controller"],
				"summary": "Get equipment details based on equipmentNumber ",
				"operationId": "getEquipmentByIdUsingGET",
				"produces": ["application/json"],
				"parameters": [{
					"name": "equipmentNumber",
					"in": "path",
					"description": "equipmentNumber",
					"required": true,
					"type": "string"
				}],
				"responses": {
					"200": {
						"description": "OK",
						"schema": {
							"$ref": "#/definitions/EquipmentRecord"
						}
					},
					"401": {
						"description": "Unauthorized"
					},
					"403": {
						"description": "Forbidden"
					},
					"404": {
						"description": "Not Found"
					}
				},
				"deprecated": false
			}
		}
	},
	"definitions": {
		"Address": {
			"type": "object",
			"properties": {
				"country": {
					"type": "string"
				},
				"pincode": {
					"type": "integer",
					"format": "int32"
				},
				"state": {
					"type": "string"
				},
				"streatName": {
					"type": "string"
				},
				"streatNumber": {
					"type": "string"
				}
			},
			"title": "Address"
		},
		"EquipmentRecord": {
			"type": "object",
			"properties": {
				"address": {
					"$ref": "#/definitions/Address"
				},
				"contractEndDate": {
					"type": "string"
				},
				"contractStartDate": {
					"type": "string"
				},
				"equipmentId": {
					"type": "integer",
					"format": "int32"
				},
				"status": {
					"type": "string"
				}
			},
			"title": "EquipmentRecord"
		}
	}
}
 
