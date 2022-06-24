import { Progress, ProgressProps } from 'antd'
import React from 'react'

interface PropsType extends ProgressProps {
    max: number,
    reached: number,
}

export default function CircularProgress(props : PropsType) {
    const {max, reached} = props
  return (
    <Progress {...props} type="circle" percent={100 / max * reached} />
  )
}
