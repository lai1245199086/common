#!/usr/bin/env bash
ps aux|grep java|grep common|grep -v grep|awk '{print $2}'|xargs kill -9