import { Divider } from 'antd'
import React from 'react'
import "./LineDivider.css"

export default function LineDivider() {
  return (
    <div className='line-divider-container'><Divider style={{color: "1F1F1F", borderTop: "2px solid"}}/></div>
  )
}
