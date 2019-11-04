rootProject.name = "osm"

include("osm-core", "osm-boot")

project(":osm-core").projectDir = File("$rootDir/osm-core")
project(":osm-boot").projectDir = File("$rootDir/osm-boot")
