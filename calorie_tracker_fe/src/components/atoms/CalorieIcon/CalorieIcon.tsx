import { FireFilled } from '@ant-design/icons'
import React from 'react'

interface PropsType {
    size: string
}

export default function CalorieIcon({size}: PropsType) {
  return (
    <FireFilled style={{ color: "#bae637", fontSize: size }} />
  )
}
