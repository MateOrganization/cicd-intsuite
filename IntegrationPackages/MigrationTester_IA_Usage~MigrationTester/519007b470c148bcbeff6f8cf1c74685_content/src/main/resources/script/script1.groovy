import com.sap.gateway.ip.core.customdev.util.Message
import groovy.util.XmlSlurper


def Message processData(Message message) {
    
    def messageLog = messageLogFactory.getMessageLog(message)
    def reader = message.getBody(Reader)
    def xml = new XmlSlurper().parse(reader)
    
    if (messageLog != null) {
        xml.'**'.findAll { node -> 
            node.children().size() == 0 && node.text()?.trim() 
        }.eachWithIndex { node, idx ->
            def fullValue = node.text()
            def value = fullValue.contains('cReturn: ') ? fullValue.substring(fullValue.indexOf('cReturn: ') + 9) : fullValue
            def paddedIdx = String.format("%02d", idx + 1)
            messageLog.addCustomHeaderProperty("${node.name()}_${paddedIdx}", value.take(200))
        }
    }
    
    return message
}