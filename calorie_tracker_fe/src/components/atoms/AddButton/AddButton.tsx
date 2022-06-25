import { PlusOutlined } from '@ant-design/icons'
import { Button } from 'antd'
import React from 'react'
import "./AddButton.css"

export default function AddButton() {
  return (
    <Button className='add-button' type='primary'><PlusOutlined style={{color: "#000000"}}/></Button>
  )
}
