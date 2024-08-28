#!/usr/bin/env bash

# Specify the module you want to update
module="mod1"
FILEPATH=../version.yaml

# Read the current version of the module from the version.yaml file
current_version=$(grep "$module: " $FILEPATH | cut -d '"' -f 2)

# Remove the 'v' prefix from the version
current_version=${current_version#v}

echo "current_version: $current_version"

# Split the version into major, minor, and patch versions
IFS='.' read -ra version_parts <<< "$current_version"

major=${version_parts[0]}
minor=${version_parts[1]}
patch=${version_parts[2]}

# Increment the minor version
minor=$((minor+1))

# Reset the patch version to 0
patch=0

# Construct the new version with the 'v' prefix
new_version="v$major.$minor.$patch"

# Replace the version of the module in the version.yaml file
echo "mod1: $new_version" > $FILEPATH

echo "Updated $module version to $new_version"